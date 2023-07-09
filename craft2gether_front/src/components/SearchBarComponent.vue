<template>
    <input class="search-bar" type="text" placeholder="Recette..." aria-label="Search" v-model="searchQuery">
    <div class="list-group" v-for="recette in recetteCherchee" :key="recette.id">
        <a href="#" class="list-group-item list-group-item-action" 
        @click="selectionnerRecette(recette)">{{ recette.titre }}</a>
    </div>
    <button @click="resetSelection">Reset Selection</button>
</template>

<script>

export default {
    data() {
        return {
            recettes: [],
            recettesSelectionnees: [],
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
                return ""
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
    methods(){
        return{
            selectionnerRecette(recette){
                return this.recettesSelectionnees += recette;
            },
            resetSelection(){
                this.recettesSelectionnees = [];
            }
        }
    }
}
</script>
<style scoped>
body{
    font-size: 20px;
}
</style>