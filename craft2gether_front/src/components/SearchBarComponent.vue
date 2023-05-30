<template>
    <input class="search-bar" type="text" placeholder="Recette..." aria-label="Search" v-model="searchQuery">
    <div class="list-group" v-for="recette in recetteCherchee" :key="recette.id">
        <a href="#" class="list-group-item list-group-item-action">{{ recette.titre }}</a>
    </div>
</template>

<script>
import router from '@/router';

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
            if(this.recettes.value != []){
                router.push({name:'home'})
            }
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
body{
    font-size: 20px;
}
</style>