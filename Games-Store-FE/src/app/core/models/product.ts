export interface Product {
    id: number;
    name: string;
    description: string;
    price: number;
    releaseDate: Date;
    status: boolean;
    productImages: Image[];
    category: Categories;
    features: Features[];
}

export interface Features {
    id: number;
    featuresName: string;
}

export interface Categories {
    id: number;
    categoriesName: string;
}

export interface Image {
    id: number;
    name: string;
    path: string;
}