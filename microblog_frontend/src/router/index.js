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
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
