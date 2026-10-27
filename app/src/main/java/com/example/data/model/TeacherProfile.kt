package com.example.data.model

data class TeacherProfile(
    val fullName: String = "Başöğretmen Zeynep Akdağ",
    val title: String = "Başöğretmen",
    val branch: String = "Türkçe",
    val schoolName: String = "Emniyet Ortaokulu",
    val city: String = "Adıyaman",
    val district: String = "Merkez",
    val academicYear: String = "2026-2027",
    val term: String = "1. Dönem"
)

object TeacherProfileRepository {
    var currentProfile = TeacherProfile()
}
