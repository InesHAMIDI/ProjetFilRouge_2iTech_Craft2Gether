<template>
  <n-table :single-line="false">
    <thead>
      <tr>
        <th>Liste</th>
        <th>Status</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>{{liste}}</td>
      </tr>
      <tr>
        <td>{{ liste.status }}</td>
      </tr>
    </tbody>
  </n-table>
</template>

<script>
export default {
    name: "ListeComponent",
    props: {
        liste: {},
    },
}
</script>
<style scoped>
    
</style>