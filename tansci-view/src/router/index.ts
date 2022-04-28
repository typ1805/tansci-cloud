import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
    {
        path: "/",
        redirect: "/home",
    },
    {
        path: "/home",
        name: "home",
        component: () => import("@/views/Index.vue"),
    },
];
const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router