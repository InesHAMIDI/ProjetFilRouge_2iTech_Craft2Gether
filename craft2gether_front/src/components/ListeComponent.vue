<template>
  <div>
    <table class="table table-striped">
      <thead>
        <tr>
          <th scope="col">Titre</th>
          <th scope="col">Status</th>
          <th scope="col"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="elt in listes" :key="elt">
          <td>{{ elt.titre }}</td>
          <td>{{ elt.status }}</td>
          <td><router-link :to="{
            name: 'liste-details',
            params: { id: elt.id }
          }"><i class="fa-regular fa-folder-open"></i></router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "ListeComponent",
  methods: {
    AfficheStatus(status, ind){
      if(status == "EN_COURS")
        this.listes[ind].status = ""
      else
      this.listes[ind].status = ""
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
<style scoped></style>