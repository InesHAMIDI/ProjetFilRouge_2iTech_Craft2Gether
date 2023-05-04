<template>
    <div>
        {{ bloc.nom }} {{ bloc.biomeOrigine }} {{ bloc.outilNecessaire }} {{ bloc.type }}
    </div>
</template>
<script>
export default {
    name: "BlocComponent",
    props:{
        bloc:  { }
    }
}
</script>
<style scoped>
    
</style>