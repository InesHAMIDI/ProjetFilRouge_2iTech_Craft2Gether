<template>
    <div>
        <BlocComponent v-for="elt in ingredients" :key="elt.nom" :produit="elt"></BlocComponent>
    </div>
</template>
<script>
import BlocComponent from "@/components/BlocComponent.vue"
export default {
    name:"RecetteComponent",
    components: {
        BlocComponent
    },
    data(){
        return{
            ingredients:[]
        }
    }
}
</script>
<style>
    
</style>