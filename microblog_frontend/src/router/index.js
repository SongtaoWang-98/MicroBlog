import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import HomeByNew from '../views/HomeByNew.vue'
import HomeByFriends  from "@/views/HomeByFriends";
import HomeByGroup from "@/views/HomeByGroup";
import FollowingList from '../views/FollowingList'
import FollowerList from '../views/FollowerList'
import HelloWorld from "@/components/HelloWorld";

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
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
