import { defineStore } from 'pinia';

export const useConnection = defineStore('connection', {
    state() {
        return {
            userCredentials: "",
            userConnected: false
        }
    },
    
    getters: {
        getCredentials(state){
            return state.userCredentials
        },
        isConnected(state){
            return state.userConnected;
        }
    },
    
    actions: {
        login() {
            this.userConnected = true;
        },

        logout() {
            this.userConnected = false;
            if(localStorage.getItem("credentials") != undefined) {
                    localStorage.removeItem('credentials');
            }
        },

        lastConnectionAvailable() {
            let cred = localStorage.getItem("credentials");
            if(cred != undefined) {
                    this.userConnected = true;
                }
            }
        },
    }
)
