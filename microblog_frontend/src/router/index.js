import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import HomeByNew from '../views/HomeByNew.vue'
import HomeByFriends  from "@/views/HomeByFriends";
import HomeByGroup from "@/views/HomeByGroup";
import FollowingList from '../views/FollowingList'
import FollowerList from '../views/FollowerList'
import PersonalPage from "@/views/PersonalPage";
import MyCollections from "@/views/MyCollections";
import MyInfoPage from "@/views/MyInfoPage";
import MyStatPage from "@/views/MyStatPage";
import PublishPage from "@/views/PublishPage";
import LoginPage from "@/views/LoginPage";
import SignUpPage from "@/views/SignUpPage";
import ManagerPage from "@/views/ManagerPage";
import SearchByContent from "@/views/SearchByContent";
import SearchByTopic from "@/views/SearchByTopic";
import SearchByUsername from "@/views/SearchByUsername";
import MessageList from "@/views/MessageList";
import MessagePage from "@/views/MessagePage";
import photoPage from "@/views/PhotoPage";

Vue.use(VueRouter)

const routes = [
    {
        path: '/home',
        name: 'Home',
        component: Home
    },
    {
        path: '/home/new',
        name: 'HomeByNew',
        component: HomeByNew
    },
    {
        path: '/home/friends',
        name: 'HomeByFriends',
        component: HomeByFriends
    },
    {
        path: '/home/group/:id',
        name: 'HomeByGroup',
        component: HomeByGroup
    },
    {
        path: '/followingList',
        name: 'FollowingList',
        component: FollowingList
    },
    {
        path: '/followerList',
        name: 'FollowerList',
        component: FollowerList
    },
    {
        path: '/personalPage/:id',
        name: 'PersonalPage',
        component: PersonalPage
    },
    {
        path: '/myCollections',
        name: 'MyCollections',
        component: MyCollections
    },
    {
        path: '/myInfoPage',
        name: 'MyInfoPage',
        component: MyInfoPage
    },
    {
        path: '/myStatPage',
        name: 'MyStatPage',
        component: MyStatPage
    },
    {
        path: '/publishPage',
        name: 'PublishPage',
        component: PublishPage
    },
    {
        path: '/loginPage',
        name: 'LoginPage',
        component: LoginPage
    },
    {
        path: '/signUpPage',
        name: 'SignUpPage',
        component: SignUpPage
    },
    {
        path: '/managerPage',
        name: 'ManagerPage',
        component: ManagerPage
    },
    {
        path: '/searchByContent/:str',
        name: 'SearchByContent',
        component: SearchByContent
    },
    {
        path: '/searchByTopic/:str',
        name: 'SearchByTopic',
        component: SearchByTopic
    },
    {
        path: '/searchByUsername/:str',
        name: 'SearchByUsername',
        component: SearchByUsername
    },
    {
        path: '/messageList',
        name: 'MessageList',
        component: MessageList
    },
    {
        path: '/messagePage/:id',
        name: 'MessagePage',
        component: MessagePage
    },
    {
        path: '/photoPage',
        name: 'photoPage',
        component: photoPage
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
