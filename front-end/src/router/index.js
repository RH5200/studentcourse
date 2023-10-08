import Vue from 'vue'
import VueRouter from 'vue-router'

import login from '../components/login.vue';
import admin from "@/components/admin/admin.vue";
import adminInfo from "@/components/admin/adminInfo.vue";
import adminEdit from "@/components/admin/adminEdit.vue";
import students from "@/components/admin/students.vue";
import teachers from "@/components/admin/teachers.vue";
import student from "@/components/student/student.vue";
import studentInfo from "@/components/student/studentInfo.vue";
import studentEdit from "@/components/student/studentEdit.vue";
import chooseCourse from "@/components/student/chooseCourse.vue";
import myCourse from "@/components/student/myCourse.vue";
import myScore from "@/components/student/myScore.vue";
import teacher from "@/components/teacher/teacher.vue";
import teacherInfo from "@/components/teacher/teacherInfo.vue";
import teacherEdit from "@/components/teacher/teacherEdit.vue";
import teacherCourse from "@/components/teacher/teacherCourse.vue";
import studentScore from "@/components/teacher/studentScore.vue";
import courses from "@/components/admin/courses.vue";
import studentClass from "@/components/teacher/studentClass.vue";
import courseInfo from "@/components/admin/courseInfo.vue";
import studentsClass from "@/components/admin/studentsClass.vue";
import studentsScore from "@/components/admin/studentsScore.vue";
import creatCourse from "@/components/admin/creatCourse.vue";
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'login',
        component: login
    },
    {
        path: '/admin',
        name: 'admin',
        component: admin,
        children: [
            {
                path: 'adminInfo',
                name: 'adminInfo',
                component: adminInfo,
            },
            {
                path: 'adminEdit',
                name: 'adminEdit',
                component: adminEdit
            },
            {
                path: 'students',
                name: 'students',
                component: students
            },
            {
                path: 'teachers',
                name: 'teachers',
                component: teachers
            },
            {
              path: 'courses',
              name: 'courses',
              component: courses
            },
            {
                path: 'courseInfo',
                name: 'courseInfo',
                component: courseInfo
            },
            {
                path: 'studentsClass',
                name:'studentsClass',
                component: studentsClass
            },
            {
                path: 'studentsScore',
                name:'studentsScore',
                component: studentsScore
            },
            {
                path: 'creatCourse',
                name: 'creatCourse',
                component: creatCourse
            },
        ]
    },
    {
        path: '/student',
        name: 'student',
        component: student,
        children: [
            {
                path: 'studentInfo',
                name: 'studentInfo',
                component: studentInfo,
            },
            {
                path: 'studentEdit',
                name: 'studentEdit',
                component: studentEdit
            },
            {
                path: 'chooseCourse',
                name: 'chooseCourse',
                component: chooseCourse
            },
            {
                path: 'myCourse',
                name: 'myCourse',
                component: myCourse
            },
            {
                path: 'myScore',
                name: 'myScore',
                component: myScore
            },
        ]
    },
    {
        path: '/teacher',
        name: 'teacher',
        component: teacher,
        children: [
            {
                path: 'teacherInfo',
                name: 'teacherInfo',
                component: teacherInfo,
            },
            {
                path: 'teacherEdit',
                name: 'teacherEdit',
                component: teacherEdit,
            },
            {
                path: 'teacherCourse',
                name: teacherCourse,
                component: teacherCourse
            },
            {
                path: 'studentScore',
                name: 'studentScore',
                component: studentScore
            },
            {
                path: 'studentClass',
                name:'studentClass',
                component: studentClass
            }
        ]
    }
]

const router = new VueRouter({
    routes
})

export default router
