<template>
	<div class="flex flex-col flex-1 items-center justify-center">
		<NForm ref="formRef" inline :model="formModel" :rules="formRules" class="!flex !flex-row !justify-center !items-center !pt-4 !w-[75%]">
			<NFormItem label="Title" path="title" class="!flex !flex-col !flex-1">
				<NInput v-model:value="formModel.title" placeholder="Title" />
			</NFormItem>
			<NButton @click="onSubmit">Submit</NButton>
		</NForm>
		<NDataTable remote striped max-height="512" virtual-scroll :columns="columns" :data="data" :loading="loading" class="!w-[75%] !pt-4" />
	</div>
</template>

<script setup lang="ts">
import { FormInst, NForm, NFormItem, NInput, NButton, NDataTable, NCheckbox, DataTableColumns } from 'naive-ui'
import { TodoEntry } from "~/model/todoentry";
import { h } from 'vue'

const runtimeConfig = useRuntimeConfig()

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

const loading: Ref<boolean> = ref(true)
const data: Ref<TodoEntry[] | null> = reactive(ref([]))

const { data: todoList, execute: loadData } = useFetch<TodoEntry[]>(runtimeConfig.public.serverBaseUrl + "/api/todos/all", {
    immediate: false,
	lazy: true,
})

onMounted(() => {
    watch(todoList, (newTodoList) => {
        data.value = newTodoList
    })
	loadData().then(() => {
		loading.value = false
	})
})

const columns: DataTableColumns<TodoEntry> = reactive([
    {
        title: 'Completed?',
        key: 'completed',
        sorter: false,
        sortOrder: false,
        width: 120,
        render: (row: TodoEntry) => {
            return h(NCheckbox, { checked: row.completed })
        }
    },
    {
        title: 'ID',
        key: 'id',
        sorter: false,
        sortOrder: false,
        width: 260,
    },
    {
        title: 'Title',
        key: 'title',
        sorter: false,
        sortOrder: false,

    },
    {
        title: 'Created at',
        key: 'created_at',
        sorter: false,
        sortOrder: false,
        width: 260,
    }
])

const onSubmit = async (e: MouseEvent) => {
    e.preventDefault()

    formRef.value?.validate((errors) => {
        if (!errors) {
			const newTodo: TodoEntry = {
                id: null,
				title: formModel.value.title,
				completed: false,
				created_at: null,
			}

            fetch(runtimeConfig.public.serverBaseUrl + "/api/todos/create", {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(newTodo)
			}).then(() => {
                loading.value = true
				loadData().then(() => {
					loading.value = false
				})
			})
        }
    })
}
</script>