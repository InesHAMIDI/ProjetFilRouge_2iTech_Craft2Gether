export function axiosInterceptor(config) {
    let token = localStorage.getItem('token');
    if (token != null && token != undefined) {
        config.headers.setAuthorization(`Basic ${token}`)
    }
    return config;
}
