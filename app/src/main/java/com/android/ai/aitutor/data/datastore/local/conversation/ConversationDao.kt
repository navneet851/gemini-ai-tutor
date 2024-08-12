package com.android.ai.aitutor.data.datastore.local.conversation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.ai.aitutor.domain.entities.Conversation
import kotlinx.coroutines.flow.Flow


@Dao
interface ConversationDao {

    @Query("SELECT * FROM Conversation")
    fun getAllConversations() : Flow<List<Conversation>>

    @Insert
    suspend fun addConversation(conversation: Conversation)

    @Query("DELETE FROM Conversation where id = :id")
    suspend fun deleteConversation(id : Int)

}