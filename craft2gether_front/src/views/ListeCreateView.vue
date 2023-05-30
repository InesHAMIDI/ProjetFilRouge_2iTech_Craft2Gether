<template>
    <div>
        <h2>Créer une liste</h2>
        <Form @submit="creerListe" ref="listeForm">
            <div>
                <label for="titre">Titre</label>
                <Field id="titre" type="text" name="titre" :rules="validateTitre"/>
                <ErrorMessage name="titre" />
            </div>
            <p>{{ this.recettes }}</p>
            <SearchBarComponent />
            
            <button>Valider</button>
        </Form>
    </div>
</template>
<script>
import { Field, Form, ErrorMessage } from 'vee-validate'
import * as yup from 'yup'
import SearchBarComponent from '@/components/SearchBarComponent.vue'
export default {
    components: {
        Field,
        Form,
        ErrorMessage,
        SearchBarComponent,
    },

    data() {
        return {
            validateTitre: yup
                .string()
                .required("Champ Obligatoire"),
            
            erreur: "", 
            listeAEnregistrer: {}
        }
    },    

    methods: {
        creerListe(values) {
            this.listeAEnregistrer = values;
            this.listeAEnregistrer.status = "EN_COURS"
            
            this.axios.post(`${this.baseUrl}/listes`,  this.listeAEnregistrer)
                .then(response => {
                    this.$emit('sendData', response.data)
                    this.$refs.listeForm.resetForm()
                })
                .catch(err => this.erreur = err);
        },
    },

    computed:{
        recettes(){
            return SearchBarComponent.recettesSelectionnees;
        },
    }
}
</script>
<style scoped>
    
</style>