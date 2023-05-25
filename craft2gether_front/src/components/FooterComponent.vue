<template>
    <div class="footer">
        <div class="credits">
            <a v-bind="githubLien"><i class="fab fa-github"></i></a>
            <div>
                <p>Created and Developped by : Ines HAMIDI</p>
                <p>2023</p>
            </div>
        </div>
        <div class="dl">
            <a v-bind="downloadLien"><i class="fas fa-download"></i></a>
        </div>
    </div>
</template>
<script>
import { ref } from 'vue'
export default {
    name: 'FooterComponent',

    data() {
        return {
            githubLien: ref({ href: "https://github.com/InesHAMIDI/ProjetFilRouge_2iTech_Craft2Gether", target: "_blank" }),
            downloadLien: ref({ href: "", target: "_blank" })
        }
    },
}
</script>
<style scoped>
.footer {
    position: fixed;
    height: auto;
    width: 100%;
    bottom: 0;
}
.credits{
    margin-top: 10px;
    margin-bottom: 10px;
    align-items: center;
    flex-direction: column;
    text-align: center;
}
.dl{
    justify-content: center;
    text-align: end;
    margin-top: 10px;
    margin-bottom: 10px;

}


</style>