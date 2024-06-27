package dev.kichan.sns.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import dev.kichan.sns.model.data.Board
import dev.kichan.sns.model.data.Chat
import dev.kichan.sns.model.data.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    var currentUser by mutableStateOf<User?>(null)
    var boardList by mutableStateOf<List<Board>>(listOf())
    var selectBoard by mutableStateOf<Board?>(null)
    var chatList by mutableStateOf<List<Chat>>(listOf())

    fun signUp(user: User, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val authResult = auth.createUserWithEmailAndPassword(user.email, password).await()
                firestore.collection("users").document(user.email).set(user).await()
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Sign up failed")
            }
        }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()

                val userDocument = firestore.collection("users").document(email).get().await()
                currentUser = userDocument.toObject(User::class.java)
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Login failed")
                Log.e("[Login]", e.message.toString())
            }
        }
    }

    fun logout() {
        auth.signOut()
        currentUser = null
    }

    fun addBoardPost(board: Board, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                firestore.collection("boards").add(board).await()
                onSuccess()
            } catch (e: Exception) {
                onError(e.message.toString())
            }
        }
    }

    fun getBoard() {
        viewModelScope.launch {
            val result = firestore.collection("boards")
                .orderBy("timestamp")
                .get()
                .await()
            boardList = result.toObjects(Board::class.java)
        }
    }

    fun addChatMessage(chat: Chat, onSuccess: () -> Unit) {
        viewModelScope.launch {
            firestore.collection("chats").add(chat).await()
            onSuccess()
        }
    }

    fun getChatMessages(senderEmail: String, receiverEmail: String) {
        viewModelScope.launch {
            val result = firestore.collection("chats").orderBy("timestamp").addSnapshotListener { snapshot, e ->
                chatList = snapshot!!.toObjects(Chat::class.java).filter {
                    it.senderEmail == senderEmail && it.receiverEmail == receiverEmail || it.senderEmail == receiverEmail && it.receiverEmail == senderEmail
                }
            }
        }
    }
}