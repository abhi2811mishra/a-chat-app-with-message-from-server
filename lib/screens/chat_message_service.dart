// lib/services/chat_message_service.dart
import 'dart:convert';
import 'package:shared_preferences/shared_preferences.dart';

class ChatMessage {
  final String sender;
  final String message;
  final DateTime timestamp;

  ChatMessage({required this.sender, required this.message, required this.timestamp});

  // Convert ChatMessage to JSON
  Map<String, dynamic> toJson() => {
        'sender': sender,
        'message': message,
        'timestamp': timestamp.toIso8601String(),
      };

  // Create ChatMessage from JSON
  factory ChatMessage.fromJson(Map<String, dynamic> json) => ChatMessage(
        sender: json['sender'],
        message: json['message'],
        timestamp: DateTime.parse(json['timestamp']),
      );
}

class ChatMessageService {
  static const _messagesKey = 'chat_messages';

  // Save a new message
  static Future<void> saveMessage(ChatMessage message) async {
    final prefs = await SharedPreferences.getInstance();
    final List<String> messagesJson = prefs.getStringList(_messagesKey) ?? [];
    messagesJson.add(jsonEncode(message.toJson()));
    await prefs.setStringList(_messagesKey, messagesJson);
  }

  // Load all messages
  static Future<List<ChatMessage>> loadMessages() async {
    final prefs = await SharedPreferences.getInstance();
    final List<String> messagesJson = prefs.getStringList(_messagesKey) ?? [];
    return messagesJson.map((jsonString) => ChatMessage.fromJson(jsonDecode(jsonString))).toList();
  }

  // Clear all messages
  static Future<void> clearMessages() async {
    final prefs = await SharedPreferences.getInstance();
    await prefs.remove(_messagesKey);
  }
}