export interface Reimbursement {
    id: number;
    requestorId: number;
    requestorName: string;
    approverId: number;
    approverName: string;
    categoryId: number;
    category: string;
    statusId: number;
    status: string;
    amount: number;
    submitted: string;
    approved: string;
}