#include "sort.h"

void swap(Task* a, Task* b) {
    Task t = *a; *a = *b; *b = t;
}

int partition(Task arr[], int low, int high) {
    int pivot = arr[high].priority; 
    int i = (low - 1);
    
    for (int j = low; j <= high - 1; j++) {
        // Tri décroissant (Plus grand prio en premier)
        if (arr[j].priority > pivot) { 
            i++;
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}

void quickSort(Task arr[], int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}