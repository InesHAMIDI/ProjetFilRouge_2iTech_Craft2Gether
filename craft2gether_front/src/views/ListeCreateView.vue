<template>
    <div class="creerListe">
        <h2>Créer une liste</h2>
        <Form @submit="creerListe" ref="listeForm">
            <div>
                <label for="titre">Titre</label>
                <Field id="titre" type="text" name="titre" :rules="validateTitre"/>
                <ErrorMessage name="titre" />
            </div>
            <div class="selection-deselectionRecettes">
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

                <div class="recettesSelectionees">
                    <table class="table table-striped">
                        <input id="searchbar" type="text" placeholder="Recette.." v-model="searchQuery"/> 
                        <thead>
                            <th scope="col"></th> <!-- Titre -->
                            <th scope="col"></th> <!-- Sélectionner -->
                        </thead>
                        <tbody>
                            <tr v-for="recette in recettesSelectionees" :key="recette.nom">
                                <td>{{ recette.nom }}</td>
                                <td><button @click="deselectionnerRecette(recette, ind)"><i class="fa-solid fa-xmark"></i></button></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
        </div>
            <button type="submit"><i class="fa-solid fa-check"></i></button>
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
            listeAEnvoyer: {},

        }
    },    

    mounted() {
        this.axios.get(`${this.baseUrl}/recettes`)
            .then(response => this.recettes = response.data)
            .catch(err => this.erreur = err);
    },

    methods: {
        creerListe(values) {
            this.listeAEnvoyer.titre = values.titre;
            this.listeAEnvoyer.recettes = this.recettesSelectionees;
            this.axios.post(`${this.baseUrl}/listes`, this.listeAEnvoyer)
                .then(response => {
                    this.$emit('sendData', response.data)
                    this.$refs.listeForm.resetForm()
                    this.$router.push({ name: 'home'})
                })
                .catch(err => this.erreur = err);
        },

        selectionnerRecette(recette){
            this.recettesSelectionees.push(recette);
        },

        deselectionnerRecette(recette, ind){
            this.recettesSelectionees.splice(ind, 1);

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

.rechercheRecettes, .recettesSelectionees{
    width: 90px;
    margin-top: 10px;
}

.creerListe{
    justify-content: center;
}

.selection-deselectionRecettes{
    flex-direction: row;
    padding-left: 10px;

}
</style>