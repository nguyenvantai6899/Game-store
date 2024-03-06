import { Discount } from "./discount";

export interface Product {
    id: number;
    name: string;
    description: string;
    price: number;
    releaseDate: Date;
    status: boolean;
    productImages: Image;
    category: Categories;
    productsFeatures: Features[];
    downloads: number;
    discount: Discount;
    categories: Categories;
    developer: Developers;

}

export interface Features {
    id: number;
    featureName: string;
}

export interface Categories {
    id: number;
    categoryName: string;
}

export interface Developers {
    id: number;
    developerName: string;
}

export interface Image {
    id: number;
    imageIcons: ImageIcon[];
    imageBanners: ImageBanner[];
    imageAvatars: ImageAvatar[];
}
export interface ImageBanner {
    id: number;
    name: string;
    path: string;
}

export interface ImageAvatar {
    id: number;
    name: string;
    path: string;
}

export interface ImageIcon {
    id: number;
    name: string;
    path: string;
}

export interface ProductDiscount {
    isHidden: boolean;
    id: number;
    product: Product;
    discount: Discount;
    // priceDiscount: number;
}
