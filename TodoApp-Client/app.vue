<template>
    <NConfigProvider :theme="darkTheme">
        <NuxtLayout>
            <NuxtPage />
        </NuxtLayout>
        <NGlobalStyle />
    </NConfigProvider>
</template>

<script setup lang="ts">
import { darkTheme, NConfigProvider, NGlobalStyle } from 'naive-ui'
</script>