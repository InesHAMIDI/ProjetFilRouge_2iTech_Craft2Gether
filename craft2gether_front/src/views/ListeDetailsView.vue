<template>
    <div class="dropdown">
        <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            {{ titre }}
        </a>

        <table class="dropdown-menu table table-striped">
            <thead>
                <tr>
                    <th scope="col">Bloc</th>
                    <th scope="col">Status</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="elt in ingredients" :key="elt">
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
    data() {
        return {
            titre: "",
            ingredients: [{name: "aaa", status: "aaa"}]
        }
    },


}
</script>
<style></style>