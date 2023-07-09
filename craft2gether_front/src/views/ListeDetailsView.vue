<template>
    <h1>{{ liste.titre }}</h1>
    <p>{{ liste.status }}</p>

    <div class="content">
        <p>Ingredients</p>
        <table class="table table-striped">
            <thead>
                <th scope="col"></th> <!-- Nom -->
                <th scope="col"></th> <!-- Quantite farmée / quantité -->
                <th scope="col"></th> <!-- Status -->
            </thead>
            <tbody>
                <tr v-for="ingredient in this.ingredients" :key="ingredient.id">
                    <td>{{ ingredient.nom }}</td>
                </tr>
            </tbody>
        </table>

        <p>Recettes</p>
        <table class="table table-striped">
            <thead>
                <th scope="col"></th> <!-- Titre -->
                <th scope="col"></th> <!-- Status -->
            </thead>
            <tbody>
                <tr v-for="recette in this.recettes" :key="recette.id">
                    <td>{{ recette.nom }}</td>
                    <td>
                        <button v-if="recette.status == 'FINISHED'" @click="recettePasFinie(recette)"><i
                                class="fa-sharp fa-regular fa-circle-check"></i></button>
                        <button v-else><i class="fa-regular fa-circle" @click="recetteFinie(recette)"></i></button>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="boutons-list">
            <button @click="deleteList"><i class="fas fa-trash"></i></button>
            <button v-if="this.liste.status == 'FINISHED'" @click="listePasFinie"><i
                    class="fa-sharp fa-regular fa-circle-check"></i></button>
            <button v-else><i class="fa-regular fa-circle" @click="listeFinie"></i></button>
        </div>
    </div>
    <p>{{ erreur }}</p>
</template>
<script>
export default {
    data() { 
        return {
            liste: {},
            ingredients: [],
            erreur: ""
        }
    },

    computed: {
        id() {
            return this.$route.params.id;
        },
        recettes() {
            return this.liste.recettes;
        },
    },

    mounted() {
        this.axios.get(`${this.baseUrl}/listes/${this.id}`)
            .then(response => { 
                this.liste = response.data
                console.log(this.liste.recettes)
                for (let elt of this.liste.recettes) {
                    this.axios.get(`${this.baseUrl}/recettes/` + elt.id)
                        .then((result) => {
                            let boop = JSON.parse(JSON.stringify(result.data)).ingredients

                            for(let ingredient of boop){
                                if(!this.ingredients.find((currentI) => {
                                    return currentI.nom == ingredient.nom
                                }))
                                {
                                    this.ingredients.push(ingredient)
                                    console.log(ingredient)
                                }
                            }
                        })
                        .catch(err => this.erreur = err);
                }
            })
            .catch(err => this.erreur = err);

        
    },

    methods: {

        recetteFinie(elt) {
            elt.status = "FINISHED";
            this.updateRecette(elt);
        },

        recettePasFinie(elt) {
            elt.status = "EN_COURS";
            this.updateRecette(elt);
        },

        listeFinie(elt) {
            elt.status = "FINISHED";
            this.updateListe(elt);
        },

        listePasFinie(elt) {
            elt.status = "EN_COURS";
            this.updateListe(elt);
        },

        updateRecette(recette) {
            for (let ing in recette.ingredients) {
                ing.quantiteFarmee = ing.quantite
            }
        },

        updateListe(val) {
            this.axios.put(`${this.baseUrl}/listes/${this.id}`, val)
                .then()
                .catch(err => alert(err))
        },

        deleteList() {
            this.axios.delete(`${this.baseUrl}/listes/${this.id}`)
                .then(() => this.$router.push({ name: 'home' }))
                .catch(err => this.erreur = err);
        },

       
    },
}
</script>
<style scoped>
.fa,
.fas,
.fa-solid,
.fa-regular {
    color: #717171;
}

.boutons-list {
    justify-content: space-evenly;
    padding-left: 10px;
    display: flex;
}
p{
    font-weight: bold;
}
.content{
    margin-left: 15px;

}
</style>