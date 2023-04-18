<template>
    <NConfigProvider :theme="theme">
        <NuxtLayout>
            <NuxtPage />
        </NuxtLayout>
        <NGlobalStyle />
    </NConfigProvider>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useOsTheme, darkTheme, NConfigProvider, NGlobalStyle } from 'naive-ui'

const osTheme = useOsTheme()
const theme = computed(() => (osTheme.value === 'dark' ? darkTheme : null))
</script>