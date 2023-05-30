<template>
    <form class="d-flex" role="search">
        <input class="form-control me" type="search" placeholder="Recette" aria-label="Search" v-model="searchQuery">
        <button class="btn btn-outline-success" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
    </form>

    <div class="ui cards" style="margin: 10px">
        <div class="card ui fluid" v-for="recette in recetteCherchee" :key="recette.id">
            <div class="content">
                <div class="header">{{ recette.titre }}</div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            recettes: []
        }
    },
    mounted() {
        this.axios.get(`${this.baseUrl}/recettes`)
        .then(response => this.recettes = response.data)
        .catch(err => this.erreur = err);
    },
    computed: {
        filteredList() {
            return this.recettes.filter(recette => {
                return recette.titre.toLowerCase().includes(this.search.toLowerCase())
            })
        },
        recetteCherchee(){
            return this.recettes.value.filter((recette) => {
                return (
                    recette.titre
                    .toLowerCase()
                    .indexOf(this.searchQuery.value.toLowerCase()) != -1
                );
            });
        }
    },
}
</script>
<style scoped>
form{
    font-size: 20px;
}
</style>