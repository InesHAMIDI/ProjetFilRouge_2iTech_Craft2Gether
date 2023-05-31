<template>
    <div class="creerListe">
        <h2>Créer une liste</h2>
        <Form @submit="creerListe" ref="listeForm">
            <div>
                <label for="titre">Titre</label>
                <Field id="titre" type="text" name="titre" :rules="validateTitre"/>
                <ErrorMessage name="titre" />
            </div>
            <div class="rechercheRecettes">
                <table class="table table-striped">
                    <input id="searchbar" type="text" placeholder="Recette.." v-model="searchQuery"/> 
                    <thead>
                        <th scope="col"></th> <!-- Titre -->
                        <th scope="col"></th> <!-- Sélectionner -->
                    </thead>
                    <tbody>
                        <tr v-for="recette in recettesRecherchees" :key="recette.nom">
                            <td>{{ recette.nom }}</td>
                            <td><button @click="selectionnerRecette(recette)"><i class="fa-solid fa-check"></i></button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <button><i class="fa-solid fa-check"></i></button>
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
            searchQuery: "",
            recettesSelectionees: [],


        }
    },    

    mounted() {
        this.axios.get(`${this.baseUrl}/recettes`)
            .then(response => this.recettes = response.data)
            .catch(err => this.erreur = err);
    },

    methods: {
        creerListe(values) {
            values += this.recettesSelectionees;
            this.axios.post(`${this.baseUrl}/listes`, values)
                .then(response => {
                    this.$emit('sendData', response.data)
                    this.$refs.listeForm.resetForm()
                })
                .catch(err => this.erreur = err);
        },

        selectionnerRecette(recette){
            this.recettesSelectionees += recette;
        }
    },

    computed: {
        recettesRecherchees() {
            return this.recettes
            .filter(
                ({ nom }) => [nom]
                .some(val => val.toLowerCase().includes(this.searchQuery.toLowerCase()))
            );
        }
    }

}
</script>
<style scoped>
.fa, .fas, .fa-solid, .fa-regular {
    color: #717171;
}

.rechercheRecettes{
    width: 90px;
    margin-top: 10px;
}

.creerListe{
    flex-direction: column;
    justify-content: center;
}
</style>