import {configureStore, createReducer, createAction} from '@reduxjs/toolkit'

export interface AppStateType {
    headerTitle: string
}

export const setHeaderTitle = createAction<string>('SET_HEADER_TITLE')

export const appStateReducer = createReducer({headerTitle: ''}, {
    [setHeaderTitle.type]: (state, action) => { return {headerTitle: action.payload} }
})

export var stateStore = configureStore({ reducer: appStateReducer})



export enum ResultType {
    success,
    error
}

export interface OperationResult {
    type: ResultType,
    message: string|JSX.Element 
}

export interface OperationResultState {
    lastResult?: OperationResult
}

export const setLastOperationResult = createAction<OperationResult|undefined>('SET_LAST_OPERATION_RESULT')

export const operationStateReducer = createReducer({lastResult: undefined}, {
    [setLastOperationResult.type]: (state, action) => { return {lastResult: action.payload} }
})

export var operationResultStateStore = configureStore({ reducer: operationStateReducer})
