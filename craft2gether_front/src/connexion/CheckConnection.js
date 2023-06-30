import { defineStore } from 'pinia';

export const useConnection = defineStore('connection', {
    state() {
        return {
            userConnected: false,
            userId : null,
            userRole : null
        }
    },
    
    getters: {
        isConnected(state) {
            return state.userConnected;
        },
        getUserId(state) {
            return state.userId;
        },
        getUserRole(state) {
            return state.userRole;
        }
    },
    
    actions: {
        login() {
            this.userConnected = true;
        },

        logout() {
            this.userConnected = false;
            this.userRole = null;
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
