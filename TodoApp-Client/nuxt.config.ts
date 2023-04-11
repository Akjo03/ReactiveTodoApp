// noinspection JSUnusedGlobalSymbols
export default {
    app: {
        head: {
            title: 'Todo-App',
            meta: [
                { name: 'description', content: 'The frontend service for a todo app using my new stack.' }
            ],
            link: [
                { rel: 'stylesheet', href: 'https://rsms.me/inter/inter.css' }
            ]
        }
    },
    css: [
        "@/assets/css/main.css",
        "@fortawesome/fontawesome-svg-core/styles.css"
    ],
    postcss: {
        plugins: {
            tailwindcss: {},
            autoprefixer: {},
        },
    },
    runtimeConfig: {
        public: {
            clientBaseUrl: '',
            serverBaseUrl: ''
        }
    }
}