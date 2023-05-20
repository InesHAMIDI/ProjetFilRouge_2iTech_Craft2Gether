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
          <td v-if="elt.status == 'EN_COURS'"><font-awesome-icon :icon="['fasr', 'badge']" /></td>
          <td v-if="elt.status == 'FINISHED'"><font-awesome-icon :icon="['far', 'badge-check']" /></td>
          
          <td><router-link :to="{
            name: 'liste-details',
            params: { id: elt.id }
          }"><font-awesome-icon :icon="['fas', 'folder-open']" /></router-link>
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