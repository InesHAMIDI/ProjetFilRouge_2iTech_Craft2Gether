<template>
    <h1>
        {{ liste.titre }}
    </h1>
    <div v-for="recette in recettes" :key="recette">
        <h2>{{ recette.titre }}</h2>

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
        }
    },

    computed:{
        id(){
            return this.$route.params.id;
        },
        ingredients(){
            return this.liste.recettes.ingredients()
        },
        
        recettes(){
            return this.getRecettes();
        }
    },

    mounted() {
        this.axios.get(`${this.baseUrl}/listes/${this.id}`)
            .then(response => this.liste = response.data)
            .catch(err => this.erreur = err);
    },

    methods: {
        getRecettes(){
            this.axios.get(`${this.baseUrl}/${this.id}/recettes`)
            .then(response => this.recettes = response.data)
            .catch(err => this.erreur = err);
        }
    },
}
</script>
<style></style>