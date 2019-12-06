import {configureStore, createReducer, createAction} from '@reduxjs/toolkit'

export interface AppStateType {
    headerTitle: string
}

export const setHeaderTitle = createAction<string>('SET_HEADER_TITLE')

export const appStateReducer = createReducer({headerTitle: ''}, {
    [setHeaderTitle.type]: (state, action) => {
        return {headerTitle: action.payload}
    }
})

export var stateStore = configureStore({ reducer: appStateReducer})