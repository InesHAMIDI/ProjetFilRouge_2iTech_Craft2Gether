<template>
    <div class="header">
        <nav class="navbar bg-body-tertiary">
            <div class="container-fluid">
                <router-link to="/" class="navbar-brand"><i class="fa-solid fa-cubes-stacked"></i></router-link>
                <router-link to="/users/1" class="d-flex"><i class="fa-regular fa-user"></i></router-link>
                <SearchBarComponent />
            </div>
        </nav>
    </div>
</template>
<script>
import SearchBarComponent from './SearchBarComponent.vue';
export default {
name: "HeaderComponent",
components:{
    SearchBarComponent,
}
}
</script>

<style scoped>
nav{
    margin-left: 10px;
    margin-right: 10px;
}
</style>