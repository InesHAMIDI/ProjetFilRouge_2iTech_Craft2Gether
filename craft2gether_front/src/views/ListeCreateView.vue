<template>
    <div>
        <h2>Créer une liste</h2>
        <Form @submit="creerListe" ref="listeForm">
            <div>
                <label for="titre">Titre</label>
                <Field id="titre" type="text" name="titre" :rules="validateTitre"/>
                <ErrorMessage name="titre" />
            </div>
            
            <table class="table table-striped">
                <input id="searchbar" type="text" placeholder="Recette.." v-bind="searchQuery"/> 
                <thead>
                    <th scope="col"></th> <!-- Titre -->
                </thead>
                <tbody>
                    <tr v-for="recette in recettesRecherchees" :key="recette">
                        <td>{{ recette.nom }}</td>
                    </tr>
                </tbody>
            </table>
            
            <button>Valider</button>
        </Form>
    </div>
</template>
<script>
import { Field, Form, ErrorMessage } from 'vee-validate'
import * as yup from 'yup'
export default {
    components: {
        Field,
        Form,
        ErrorMessage,
    },

    data() {
        return {
            validateTitre: yup
                .string()
                .required("Champ Obligatoire"),
            
            erreur: "",
            recettes: [],
            searchQuery: ""
        }
    },    

    mounted() {
        this.axios.get(`${this.baseUrl}/recettes`)
            .then(response => this.recettes = response.data)
            .catch(err => this.erreur = err);
    },

    methods: {
        creerListe(values) {
            this.axios.post(`${this.baseUrl}/listes`, values)
                .then(response => {
                    this.$emit('sendData', response.data)
                    this.$refs.listeForm.resetForm()
                })
                .catch(err => this.erreur = err);
        },
    },

    computed:{
        recettesRecherchees(){
            if (this.searchQuery == "") {
                return this.recettes;
            }
            return this.recettes.filter((r) => {
                return r.nom
                .toLowerCase()
                .indexOf(valueOf(this.searchQuery).toLowerCase()) != -1 
            });
        }
    }

}
</script>
<style scoped>
    
</style>