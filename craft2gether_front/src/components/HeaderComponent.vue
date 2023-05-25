<template>
    <div class="header">
        <nav class="navbar bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="home"><i class="fa-solid fa-cubes-stacked"></i></a>
                <a class="d-flex" href="user" style="align-items: start;"><i class="fa-regular fa-user"></i></a>
                <form class="d-flex" role="search">
                    <input class="form-control me" type="search" placeholder="Recette ou Bloc" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit"><i class="fa-solid fa-magnifying-glass" style="font-size: 20px;"></i></button>
                </form>
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