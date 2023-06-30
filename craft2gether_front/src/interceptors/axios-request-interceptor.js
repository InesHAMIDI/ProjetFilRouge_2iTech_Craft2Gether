export function axiosInterceptor(config)
      {
        let cred = localStorage.getItem('credentials');
        if (cred != null && cred != undefined) {
            config.headers.setAuthorization(`Basic ${cred}`)
        }
        return config;
    }