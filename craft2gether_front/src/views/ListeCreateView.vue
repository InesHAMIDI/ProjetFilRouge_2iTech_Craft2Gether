<template>
    <div class="creerListe">
        <h2>Créer une liste</h2>
        <form @submit.prevent="creerListe" ref="listeForm">
            <div>
                <label for="titre">Titre </label>
                <input id="titre" type="text" name="titre" v-model="titre"/>
                <button type="submit"><i class="fa-solid fa-plus"></i></button>
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
                                <td><button @click.prevent="selectionnerRecette(recette)"><i class="fa-solid fa-check"></i></button></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="recettesSelectionees">
                    <p>Recettes sélectionnées</p>
                    <table class="table table-striped">
                        <thead>
                            <th scope="col"></th> <!-- Titre -->
                            <th scope="col"></th> <!-- Sélectionner -->
                        </thead>
                        <tbody>
                            <tr v-for="recette in recettesSelectionees" :key="recette.nom">
                                <td>{{ recette.nom }}</td>
                                <td><button @click.prevent="deselectionnerRecette(recette, ind)"><i class="fa-solid fa-xmark"></i></button></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </div>
</template>
<script>
export default {
    components: {
    
    },

    data() {
        return {
            erreur: "",
            recettes: [],
            searchQuery: "",
            recettesSelectionees: [],
            listeAEnvoyer: {},
            titre:""

        }
    },    

    mounted() {
        this.axios.get(`${this.baseUrl}/recettes`)
            .then(response => this.recettes = response.data)
            .catch(err => this.erreur = err);
    },

    methods: {
        creerListe() {
            this.listeAEnvoyer.titre = this.titre;
            this.listeAEnvoyer.recettes = this.recettesSelectionees;
            this.axios.post(`${this.baseUrl}/listes`, this.listeAEnvoyer)
                .then(response => {
                    this.$emit('sendData', response.data)
                    this.$router.push({ name: 'home' })
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
    width: 200px;
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