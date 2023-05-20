<template>
    <div class="header">
        <nav class="navbar bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#"><i class="fal block-brick"></i></a>
                <a class="d-flex" href="#"><font-awesome-icon :icon="['far', 'user']" /></a>
                <form class="d-flex" role="search">
                    <input class="form-control me" type="search" placeholder="Recette ou Bloc" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Rechercher</button>
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
</style>