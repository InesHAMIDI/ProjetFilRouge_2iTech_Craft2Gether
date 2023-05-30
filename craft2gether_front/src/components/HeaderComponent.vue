<template>
    <div class="header">
        <nav class="navbar bg-body-tertiary">
            <div class="container-fluid">
                <router-link to="/" class="navbar-brand"><i class="fa-solid fa-cubes-stacked"></i></router-link>
                <router-link to="/users/1" class="d-flex"><i class="fa-regular fa-user"></i></router-link>
            </div>
        </nav>
    </div>
</template>
<script>
export default {
name: "HeaderComponent",

}
</script>

<style scoped>
nav{
    margin-left: 10px;
    margin-right: 10px;
}
</style>