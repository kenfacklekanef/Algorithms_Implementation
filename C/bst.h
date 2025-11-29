#include "bst.h"

BSTNode* insertBST(BSTNode* root, Task task) {
    if (root == NULL) {
        BSTNode* newNode = (BSTNode*)malloc(sizeof(BSTNode));
        newNode->data = task;
        newNode->left = newNode->right = NULL;
        return newNode;
    }
    if (task.id < root->data.id) root->left = insertBST(root->left, task);
    else if (task.id > root->data.id) root->right = insertBST(root->right, task);
    return root;
}

void DFS_InOrder(BSTNode* root) {
    if (root != NULL) {
        DFS_InOrder(root->left);
        printf("   [ID %d] %s (Prio: %d)\n", root->data.id, root->data.description, root->data.priority);
        DFS_InOrder(root->right);
    }
}

void DFS_PreOrder(BSTNode* root) {
    if (root != NULL) {
        printf("   [ID %d] %s\n", root->data.id, root->data.description);
        DFS_PreOrder(root->left);
        DFS_PreOrder(root->right);
    }
}

void BFS_LevelOrder(BSTNode* root) {
    if (root == NULL) return;
    
    // File statique simple pour la démo
    BSTNode* queue[100];
    int front = 0, rear = 0;
    
    queue[rear++] = root;
    
    printf("\n   [Ordre BFS] : ");
    while (front < rear) {
        BSTNode* current = queue[front++];
        printf("%d -> ", current->data.id);
        
        if (current->left) queue[rear++] = current->left;
        if (current->right) queue[rear++] = current->right;
    }
    printf("FIN\n");
}

// Convertit l'arbre en tableau pour le tri
void flattenBST(BSTNode* root, Task** arr, int* index) {
    if (root != NULL) {
        flattenBST(root->left, arr, index);
        (*arr)[*index] = root->data;
        (*index) = (*index) + 1;
        flattenBST(root->right, arr, index);
    }
}

void freeBST(BSTNode* root) {
    if (root != NULL) {
        freeBST(root->left);
        freeBST(root->right);
        free(root);
    }
}