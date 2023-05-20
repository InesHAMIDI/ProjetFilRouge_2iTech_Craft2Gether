<template>
    
    <div>
        {{ liste.titre }}
    </div>
    <div class="dropdown" v-for="recette in recettes" :key="recette">
        <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            {{ recette.titre }}
        </a>

        <table class="dropdown-menu table table-striped">
            <thead>
                <tr>
                    <th scope="col">Bloc</th>
                    <th scope="col">Status</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="ingredient in ingredients" :key="ingredient">
                    <td>{{ ingredient.name }}</td>
                    <td>{{ ingredient.status }}</td>
                    <td><router-link :to="{
                        name: 'liste-details',
                        params: { id: elt.id }
                    }"></router-link>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
<script>
export default {
    data() {
        return {
            liste: {},
            recette: {},
            ingredient: []
        }
    },

    computed:{
        id(){
            return this.$route.params.id;
        },
        recettes(){
            return this.liste.recettes;
        },
    },

    mounted() {
        this.axios.get(`${this.baseUrl}/listes/${this.id}`)
            .then(response => this.liste = response.data)
            .catch(err => this.erreur = err);
    },

    methods: {
        getRecette(ind){
            this.axios.get(`${this.baseUrl}/recettes/${this.liste.recettes[ind].id}`)
            .then(response => this.recette = response.data)
            .catch(err => this.erreur = err);
        }
    },
}
</script>
<style></style>