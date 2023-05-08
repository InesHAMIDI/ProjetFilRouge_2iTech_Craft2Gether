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
          <td>{{ elt.name }}</td>
          <td>{{ elt.status }}</td>
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
  name: "ListeComponent",
  props: {
    listes: [{id: 1, name: "aaaa", status: "fini" }],
  },
  computed: {
    /*statusRow(value)  {
      if(){
        statusRow = "success";
      }
      return statusRow;
    }*/
  }
}
</script>
<style scoped></style>