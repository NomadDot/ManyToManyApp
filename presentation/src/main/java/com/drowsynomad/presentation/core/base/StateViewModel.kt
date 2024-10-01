package com.drowsynomad.presentation.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drowsynomad.presentation.core.common.UiState
import com.drowsynomad.presentation.core.common.UiEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @author Roman Voloshyn (Created on 30.09.2024)
 */

abstract class StateViewModel<S : UiState, E : UiEvent>(
    initialState: S,
) : ViewModel() {

    private val uiState: MutableStateFlow<S> = MutableStateFlow(initialState)
    val publicState: StateFlow<S> by lazy { uiState.asStateFlow() }

    protected fun updateState(stateAction: (S) -> S) {
        uiState.value = stateAction.invoke(uiState.value)
    }

    abstract fun handleUiEvent(uiEvent: E)

    protected fun launch(
        exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, _ -> },
        dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
        block: suspend CoroutineScope.() -> Unit,
    ) = viewModelScope.launch(
        context = exceptionHandler + dispatcher + SupervisorJob(),
        block = block
    )
}