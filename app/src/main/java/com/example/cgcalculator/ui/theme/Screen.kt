package com.example.cgcalculator.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cgcalculator.R

@Composable
fun CGPACalculatorApp() {
    val background = painterResource(id = R.drawable.thapar)
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = background,
            contentDescription = "Background",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.2f),
            contentScale = ContentScale.Crop
        )

        val redColor = Color(0xFFD50000)
        val grayColor = Color(0xFFF2F2F2)

        // State variables for dropdown selections and CGPA
        var selectedBranch1 by remember { mutableStateOf("") }
        var selectedCourse1 by remember { mutableStateOf("") }
        var selectedGrade1 by remember { mutableStateOf("") }


        var selectedCourse2 by remember { mutableStateOf("") }
        var selectedGrade2 by remember { mutableStateOf("") }
        var selectedCourse3 by remember { mutableStateOf("") }
        var selectedGrade3 by remember { mutableStateOf("") }
        var selectedCourse4 by remember { mutableStateOf("") }
        var selectedGrade4 by remember { mutableStateOf("") }
        var selectedCourse5 by remember { mutableStateOf("") }
        var selectedGrade5 by remember { mutableStateOf("") }


        var cgpa by remember { mutableDoubleStateOf(0.0) }

        val courseCredits = mapOf(
            "Chemistry" to 4.0,
            "Programming - C" to 4.0,
            "Electrical and Electronics" to 4.5,
            "Environment" to 2.0,
            "Mathematics 1" to 3.5
        )

        val gradePoints = mapOf(
            "A+" to 10.0,
            "A" to 10.0,
            "A-" to 9.0,
            "B" to 8.0,
            "B-" to 7.0,
            "C" to 6.0,
            "C-" to 5.0
        )

        // CGPA Calculation Function
        fun calculateCGPA(): Double {
            val selectedCourses = listOf(selectedCourse1, selectedCourse2)
            val selectedGrades = listOf(selectedGrade1, selectedGrade2)
            var totalCredits = 0.0
            var totalGradePoints = 0.0

            for (i in selectedCourses.indices) {
                val course = selectedCourses[i]
                val grade = selectedGrades[i]
                val credit = courseCredits[course] ?: 0.0
                if (credit > 0 && grade in gradePoints) {
                    totalCredits += credit
                    totalGradePoints += credit * gradePoints[grade]!!
                }
            }
            return if (totalCredits == 0.0) 0.0 else totalGradePoints / totalCredits
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            // Logo Placeholder
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(color = redColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.thaparlogo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
            }

            // Branch Dropdown
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BranchDropdown(selectedBranch1) { selectedBranch1 = it }

            }

            // Course and Grade Selection Rows
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CourseDropdown(selectedCourse1) { selectedCourse1 = it }
                GradeDropdown(selectedGrade1) { selectedGrade1 = it }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CourseDropdown(selectedCourse2) { selectedCourse2 = it }
                GradeDropdown(selectedGrade2) { selectedGrade2 = it }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CourseDropdown(selectedCourse3) { selectedCourse3 = it }
                GradeDropdown(selectedGrade3) { selectedGrade3 = it }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CourseDropdown(selectedCourse4) { selectedCourse4 = it }
                GradeDropdown(selectedGrade4) { selectedGrade4 = it }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CourseDropdown(selectedCourse5) { selectedCourse5 = it }
                GradeDropdown(selectedGrade5) { selectedGrade5 = it }
            }

            // Calculate CGPA Button
            Button(
                onClick = { cgpa = calculateCGPA() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = redColor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Calculate CGPA", color = Color.White, fontSize = 16.sp)
            }

            // Display CGPA
            Text(
                text = "CGPA: %.2f".format(cgpa),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Reset Button
            Button(
                onClick = {
                    selectedBranch1 = ""

                    selectedCourse1 = ""
                    selectedCourse2 = ""
                    selectedCourse3 = ""
                    selectedCourse4 = ""
                    selectedCourse5 = ""
                    selectedGrade1 = ""
                    selectedGrade2 = ""
                    selectedGrade3 = ""
                    selectedGrade4 = ""
                    selectedGrade5 = ""
                    cgpa = 0.0
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = grayColor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Reset", color = Color.Black, fontSize = 16.sp)
            }
        }
    }
}

// Branch Dropdown Composable
@Composable
fun BranchDropdown(selectedBranch: String, onBranchSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val branches = listOf("COPC", "COE", "ECE", "ENC", "EVD", "EEC", "RAI")

    Box {
        Column(
            modifier = Modifier
                .width(200.dp)
                .background(Color(0xFFF2F2F2), RoundedCornerShape(8.dp))
                .padding(8.dp)
                .clickable { expanded = !expanded }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (selectedBranch.isEmpty()) "Select your branch" else selectedBranch,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop Down Arrow",
                    tint = Color.Gray
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                branches.forEach { branch ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = branch,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        onClick = {
                            onBranchSelected(branch)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

// Course Dropdown Composable
@Composable
fun CourseDropdown(selectedCourse: String, onCourseSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Chemistry", "Programming - C", "Electrical and Electronics", "Environment", "Mathematics 1")

    Box {
        Column(
            modifier = Modifier
                .width(200.dp)
                .background(Color(0xFFF2F2F2), RoundedCornerShape(8.dp))
                .padding(8.dp)
                .clickable { expanded = !expanded }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (selectedCourse.isEmpty()) "Select your course" else selectedCourse,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop Down Arrow",
                    tint = Color.Gray
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { course ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = course,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        onClick = {
                            onCourseSelected(course)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

// Grade Dropdown Composable
@Composable
fun GradeDropdown(selectedGrade: String, onGradeSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val grades = listOf("A+", "A", "A-", "B", "B-", "C", "C-")

    Box {
        Column(
            modifier = Modifier
                .width(200.dp)
                .background(Color(0xFFF2F2F2), RoundedCornerShape(8.dp))
                .padding(8.dp)
                .clickable { expanded = !expanded }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (selectedGrade.isEmpty()) "Select your grade" else selectedGrade,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop Down Arrow",
                    tint = Color.Gray
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                grades.forEach { grade ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = grade,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        onClick = {
                            onGradeSelected(grade)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
