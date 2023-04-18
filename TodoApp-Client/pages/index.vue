<template>
	<div class="flex flex-col flex-1 items-center justify-center">
		<NForm ref="formRef" inline :model="formModel" :rules="formRules" class="!flex !flex-row !justify-center !items-center !pt-4 !w-[75%]">
			<NFormItem label="Title" path="title" class="!flex !flex-col !flex-1">
				<NInput v-model:value="formModel.title" placeholder="Title" />
			</NFormItem>
			<NButton @click="onSubmit">Submit</NButton>
		</NForm>
		<NDataTable class="!w-[75%]">

		</NDataTable>
	</div>
</template>

<script setup lang="ts">
import { FormInst, NForm, NFormItem, NInput, NButton, NDivider, NDataTable } from 'naive-ui'

const formRef = ref<FormInst | null>(null)
const formModel = ref({
	title: null,
})
const formRules = {
    title: {
        required: true,
		message: 'Please input something!',
		trigger: 'blur'
	}
}

const onSubmit = (e: MouseEvent) => {
    e.preventDefault()
    formRef.value?.validate((errors) => {
        if (!errors) {
            const createDto = {
                id: null,
				title: formModel.value.title,
				completed: false,
				created_at: null,
			}

            console.log(createDto)
        }
    })
}
</script>