<template>
  <div class="liste">
    <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col"></th> <!-- Titre -->
          <th scope="col"></th> <!-- Status -->
          <th scope="col"></th> <!-- Open button -->
          <th scope="col"></th> <!-- Delete button -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="elt in listes" :key="elt.id">
          <td>{{ elt.titre }}</td>
          <td v-if="elt.status == 'EN_COURS'"><i class="fa-regular fa-badge"></i></td>
          <td v-if="elt.status == 'FINISHED'"><i class="fa-regular fa-badge-check"></i></td>
          
          <td><router-link :to="{
            name: 'liste-details',
            params: { id: elt.id }
          }"><i class="fa-solid fa-folder-open"></i></router-link>
          </td>
          <td><button @click="deleteList(elt.id, ind)"><i class="fas fa-trash"></i></button></td>
        </tr>
      </tbody>
    </table>
    <p v-if="listes.length == 0">Vous n'avez pas encore créé de liste !</p>
    <button><router-link to="/createListe"><i class="fa-solid fa-plus"></i></router-link></button>

  </div>
</template>

<script>
export default {
  name: "ListeComponent",
  methods: {
    deleteList(id, ind){
      this.axios.delete(`${this.baseUrl}/listes/${id}`)
                .then(( ) => { this.listes.splice(ind, 1) })
                .catch(err => this.erreur = err);
    }
  },
  mounted() {
    this.axios.get(`${this.baseUrl}/listes`)
    .then(response => this.listes = response.data)
    .catch(err => this.erreur = err);
    },

    data(){
    return{
      listes: [],
      erreur: ""
    }
  },
}
</script>
<style scoped>
.fa-solid, .fa-regular {
  color: #717171;
}
</style>