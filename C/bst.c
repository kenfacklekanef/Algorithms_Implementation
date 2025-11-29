#ifndef BST_H
#define BST_H

#include "common.h"

typedef struct BSTNode {
    Task data;
    struct BSTNode *left;
    struct BSTNode *right;
} BSTNode;

BSTNode* insertBST(BSTNode* root, Task task);
void DFS_InOrder(BSTNode* root);
void DFS_PreOrder(BSTNode* root);
void BFS_LevelOrder(BSTNode* root);
void flattenBST(BSTNode* root, Task** arr, int* index);
void freeBST(BSTNode* root);

#endif