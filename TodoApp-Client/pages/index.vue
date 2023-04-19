<template>
	<div class="flex flex-col flex-1 items-center justify-center">
		<NForm ref="formRef" inline :model="formModel" :rules="formRules" class="!flex !flex-row !justify-center !items-center !pt-4 !w-[75%]">
			<NFormItem label="Title" path="title" class="!flex !flex-col !flex-1">
				<NInput v-model:value="formModel.title" placeholder="Title" />
			</NFormItem>
			<NButton @click="onSubmit">Submit</NButton>
		</NForm>
		<NDataTable remote striped :columns="columns" class="!w-[75%]">

		</NDataTable>
	</div>
</template>

<script setup lang="ts">
import { FormInst, NForm, NFormItem, NInput, NButton, NDataTable, DataTableColumn, DataTableColumns } from 'naive-ui'
import { TodoEntry } from "~/model/todoentry";

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

const completedColumn: DataTableColumn = {
    title: 'Completed?',
    key: 'completed',
    sorter: false,
    sortOrder: false,
	width: 120,
}

const idColumn: DataTableColumn = {
    title: 'ID',
    key: 'id',
    sorter: false,
    sortOrder: false,
	width: 260,
}

const titleColumn: DataTableColumn = {
	title: 'Title',
	key: 'title',
	sorter: false,
	sortOrder: false,

}

const createdAtColumn: DataTableColumn = {
	title: 'Created At',
	key: 'created_at',
	sorter: false,
	sortOrder: false,
	width: 260,
}

const columns: DataTableColumns = [
    completedColumn,
	idColumn,
	titleColumn,
	createdAtColumn
]

const onSubmit = (e: MouseEvent) => {
    e.preventDefault()
    formRef.value?.validate((errors) => {
        if (!errors) {
            const newTodo: TodoEntry = {
                id: null,
				title: formModel.value.title,
				completed: false,
				created_at: null,
			}

            console.log(newTodo)
        }
    })
}
</script>