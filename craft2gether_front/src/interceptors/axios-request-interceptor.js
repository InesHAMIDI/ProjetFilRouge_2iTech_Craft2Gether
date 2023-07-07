export function axiosInterceptor(config) {
    let credentials = JSON.parse(localStorage.getItem('credentials'));
    if (credentials != null && credentials != undefined) {
        config.auth = credentials
    }
    return config;
}
